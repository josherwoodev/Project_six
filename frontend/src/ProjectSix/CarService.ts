import {Car} from "../CarType";

export function fetchCars():Promise<Car[]>{
    const mockedCars: Car[]=[
        {
            id:1,
            make: "Bugati",
            model: "Subabru",
            year:1832,
            price:93927,
            isUsed:false
        }
        ]
    return Promise.resolve(mockedCars);
}
