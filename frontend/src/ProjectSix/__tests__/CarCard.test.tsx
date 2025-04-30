import {describe, expect, it} from "vitest";
import {render, screen} from "@testing-library/react";
import CarCard from "../CarCard";
import {Car} from "../../CarType";

describe('CarCard', () => {

    it('should display provided car information', () => {

        const testCar:Car = {id: 1, make: "Nissan", model:"Altima", year:2024, price:2000.33, used:true}

        render(<CarCard car={testCar}/>)

        const makeModelText = screen.getByRole("heading", {name:testCar.make + " " + testCar.model})
        const yearText = screen.getByText("Year: " + testCar.year);
        const priceText = screen.getByText("Price: $"+testCar.price);
        const usedText = screen.getByText("Used Vehicle: " + (testCar.used ? "Yes" : "No"));


        expect(makeModelText).toBeVisible();
        expect(yearText).toBeVisible();
        expect(priceText).toBeVisible();
        expect(usedText).toBeVisible();
    });


});