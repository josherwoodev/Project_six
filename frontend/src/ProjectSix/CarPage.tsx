import {useEffect, useState} from "react";
import {fetchCars} from "./CarService";
import {Car} from "../CarType.ts";
import CarCard from "./CarCard";
import * as React from "react";
import axios from "axios";


const CarPage = () => {
const [cars,setCars] = useState<Car[]>([]);
const [newCar, setNewCar] = useState<Omit<Car, "id">>({
    make:"",
    model:"",
    year:0,
    price:0,
    used:false
})

    const handleInputChange = (event : React.ChangeEvent<HTMLInputElement>) =>{

        console.log(cars)

        const {name, value} = event.target;
        // console.log(name, value)
        // console.log(newCar)
        setNewCar({
            ...newCar,
            [name] :(name === "year"|| name ==="price") ? parseInt(value) : value,
        });
    }

    const handleCheckboxChange = (event: React.ChangeEvent<HTMLInputElement>) =>{
        setNewCar({...newCar, used: !newCar.used})
    }

    const handleClick = ()=>
    {
        axios.post("/api/cars", {
            "make": newCar.make,
            "model": newCar.model,
            "year": newCar.year,
            "price": newCar.price,
            "used": newCar.used
        })
            .then((r) => {
                // console.log(r);
                fetchCars().then(setCars)
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const handleDelete= (num: number) =>{
    const path = ("/api/car/"+num)
     console.log(num)
        axios.delete(path,num)
            .then(()=>{
            fetchCars().then(setCars)
        })


    }

    useEffect(() => {
        fetchCars().then(setCars)
    }, []);



    return (
        <>
            <h1>
                Howdy Honda Dealership Database
            </h1>

            <form role={"form"}>
                <input name={"make"} type="text" value={newCar.make} onChange={handleInputChange} placeholder="vehicle make"/>
                <input name={"model"} type="text" value={newCar.model} onChange={handleInputChange} placeholder="vehicle model"/>
                <input name={"year"} type="number" value={newCar.year} onChange={handleInputChange} placeholder="vehicle year"/>
                <input name={"price"} type="number" value={newCar.price} onChange={handleInputChange} placeholder="vehicle price"/>
                <input name={"year"}  type="checkbox" value={"checked"} onChange={handleCheckboxChange}  aria-label="is it used?"/>

                <button type={"button"} onClick={handleClick}>submit</button>


            </form>
            <ul>
                {cars.map((el, index) => (<li key={index}><CarCard car={el} /><button onClick={()=> handleDelete(el.id)}>Delete</button></li>))}
            </ul>
        </>
    )
}

export default CarPage;