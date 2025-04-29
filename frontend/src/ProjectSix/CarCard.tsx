import * as React from "react";
import {Car} from "../CarType";

interface CarCardProps {
    car: Car;
}

const CarCard: React.FC<CarCardProps> = ({car}) => {

    return (

        <div>
            <h1>
                {car.year + " " + car.make + " " + car.model}
            </h1>
            <span>
                {"Price: $" + car.price}
            </span>
            <br/>
            <span>
                {"Is this car used: " + car.isUsed}
            </span>
        </div>
    )
}

export default CarCard;