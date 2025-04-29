import {useEffect, useState} from "react";
import {fetchCars} from "./CarService";
import {Car} from "../CarType.ts";


const CarPage = () => {
const [cars,setCars] = useState<Car[]>();
    useEffect(() => {
        fetchCars().then(setCars)
    }, []);
    return (
        <>
            <h1>
                Howdy Honda
            </h1>
        </>

    )
}

export default CarPage;