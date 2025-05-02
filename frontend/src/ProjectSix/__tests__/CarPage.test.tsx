import {render, screen, waitFor} from "@testing-library/react";
import {describe, expect} from "vitest";
import CarPage from "../CarPage.tsx";
import * as CarService from "../CarService.ts";
import {Car} from "../../CarType";
import {userEvent} from "@testing-library/user-event";

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

    it('should find a form input', () => {
        render(<CarPage/>)
        expect(screen.getByRole("form")).toBeInTheDocument();
    });

    it('should display inputs for vehicle', () => {
        render(<CarPage/>)
        expect(screen.getByPlaceholderText("vehicle make")).toBeVisible();
        expect(screen.getByPlaceholderText("vehicle model")).toBeVisible();
        expect(screen.getByPlaceholderText("vehicle year")).toBeVisible();
        expect(screen.getByPlaceholderText("vehicle price")).toBeVisible();
        expect(screen.getByPlaceholderText("is it used?")).toBeVisible();

    });

    it('should display a button to submit form data', () =>{
        render(<CarPage/>)
        expect(screen.getByRole("button", {name: "submit"}))

    })

    it('should send data in input fields to database', async () => {

        const vehicleMake= screen.getByPlaceholderText("vehicle make")
        const vehicleModel= screen.getByPlaceholderText("vehicle model")
        const vehicleYear= screen.getByPlaceholderText("vehicle year")
        const vehiclePrice= screen.getByPlaceholderText("vehicle price")
        const vehicleUsed= screen.getByPlaceholderText("is it used?")
        const button = screen.getByRole("button", {name: "submit"})

        await userEvent.type(vehicleMake, 'Ford')
        await userEvent.type(vehicleModel, 'Fusion')
        await userEvent.type(vehicleYear, '2020')
        await userEvent.type(vehiclePrice, '29453')
        await userEvent.type(vehicleUsed, 'no')
        await userEvent.click(button)
    });

})

