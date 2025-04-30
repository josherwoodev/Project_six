import {render, screen, waitFor} from "@testing-library/react";
import {describe, expect} from "vitest";
import CarPage from "../CarPage.tsx";
import * as CarService from "../CarService.ts";
import {Car} from "../../CarType";

describe('CarPage', () => {
    it('should display the name of the Car Dealership', async () => {
        vi.spyOn(CarService, 'fetchCars').mockResolvedValue([]);
        await waitFor(()=>render(<CarPage/>))
        expect(screen.getByRole("heading", {name: "Howdy Honda"})).toBeVisible();
    });

    it('should display a fetched list of cars', async () => {
        const cars:Car[] = [{
            id:1,
            make: "Ford",
            model: "F250",
            year:2025,
            price:100,
            used:false
        },{
            id:2,
            make: "Jeep",
            model: "Dumb",
            year:1000,
            price:5033333000.44,
            used:true
        }
        ]

        const mockCarService = vi.spyOn(CarService, 'fetchCars').mockResolvedValue(cars);
        await waitFor(()=>render(<CarPage/>))

        expect(mockCarService).toHaveBeenCalledTimes(1);

        const carList = screen.getAllByRole("listitem")

        expect(carList[0].textContent).toContain(cars[0].make + " " + cars[0].model)
        expect(carList[1].textContent).toContain(cars[1].make + " " + cars[1].model)
    });

})

