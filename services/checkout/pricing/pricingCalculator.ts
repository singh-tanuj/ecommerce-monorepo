/**
 * SCRUM-8
 * Checkout pricing calculation
 * Ensures discount is applied before tax.
 */

export function computeTaxBase(subtotal: number, discount: number): number {
  const base = subtotal - discount;
  return base < 0 ? 0 : base;
}

export function computeTotal(
  subtotal: number,
  discount: number,
  taxRate: number,
  shipping: number
): number {
  const taxBase = computeTaxBase(subtotal, discount);
  const tax = taxBase * taxRate;
  return taxBase + tax + shipping;
}
