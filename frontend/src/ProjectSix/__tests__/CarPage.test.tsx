import {render, screen} from "@testing-library/react";
import {describe, expect} from "vitest";
import CarPage from "../CarPage.tsx"

describe('CarPage',()=>{
    it('should display the name of the Car Dealership', () => {
        render(<CarPage/>)
        expect(screen.getByRole("heading",{name:"Howdy Honda"})).toBeVisible();
    });

})

