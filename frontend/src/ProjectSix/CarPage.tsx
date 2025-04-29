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
                {cars.map((car) => (
                    <CarCard key={car.id} car={car}/>
                ))}
            </ul>
        </>

    )
}

export default CarPage;