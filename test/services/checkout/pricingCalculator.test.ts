import { computeTaxBase, computeTotal } 
  from "../../../services/checkout/pricing/pricingCalculator";

describe("Checkout pricing calculation (SCRUM-8)", () => {
  test("discount is applied before tax", () => {
    expect(computeTaxBase(100, 10)).toBe(90);
  });

  test("tax base never goes below zero", () => {
    expect(computeTaxBase(50, 80)).toBe(0);
  });

  test("total is calculated correctly", () => {
    // subtotal = 100
    // discount = 10 → tax base = 90
    // tax = 9 (10%)
    // shipping = 5
    expect(computeTotal(100, 10, 0.1, 5)).toBeCloseTo(104);
  });
});
