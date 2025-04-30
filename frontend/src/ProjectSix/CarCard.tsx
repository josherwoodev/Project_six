import {Car} from "../CarType";

type CarCardProps = {
    car: Car
}

const CarCard = ({car}:CarCardProps) => {
    return (
        <div>
            <h1>{car.make} {car.model}</h1>
            <p>Year: {car.year}</p>
            <p>Price: ${car.price}</p>
            <p>Used Vehicle: {car.used ? "Yes" : "No"}</p>
        </div>
    );
};

export default CarCard;