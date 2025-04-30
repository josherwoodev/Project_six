import {useEffect, useState} from "react";
import {fetchCars} from "./CarService";
import {Car} from "../CarType.ts";
import CarCard from "./CarCard";


const CarPage = () => {
const [cars,setCars] = useState<Car[]>([]);



    useEffect(() => {
        fetchCars().then(setCars)
    }, []);



    return (
        <>
            <h1>
                Howdy Honda
            </h1>
            <ul>
                {cars.map((el, index) => (<li key={index}><CarCard car={el} /></li>))}
            </ul>
        </>
    )
}

export default CarPage;