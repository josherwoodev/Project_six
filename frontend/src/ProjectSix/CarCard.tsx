import {Car} from "../CarType";

type CarCardProps = {
    car: Car
}

const CarCard = ({car}:CarCardProps) => {
    return (
        <div>
            <h2>{car.make} {car.model}</h2>
            <p>Year: {car.year}</p>
            <p>Price: ${car.price}</p>
            <p>Used Vehicle: {car.used ? "Yes" : "No"}</p>
        </div>
    );
};

export default CarCard;