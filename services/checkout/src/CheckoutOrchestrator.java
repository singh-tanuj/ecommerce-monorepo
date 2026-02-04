Checkout total mismatch when applying discount code

Problem:
Checkout "Order Total" is incorrect when a discount code is applied. Subtotal updates, but total does not reflect the discount + tax correctly.

Steps to Reproduce:

1. Add 2 items to cart (any SKUs)

2. Go to Checkout

3. Apply discount code: SAVE10

4. Observe Order Total

Expected:
Order Total = (Subtotal - Discount) + Tax + Shipping (if applicable)
All line items should be consistent.

Actual:
Order Total sometimes remains equal to the pre-discount total, or tax is calculated from the wrong base.

Environment:

Env: QA / Staging

Browser: Chrome latest

Build: current main branch

Notes:
This is a clean test ticket for validating Agent 1/2/3 wiring (Jira -> PR -> analysis).

Subtasks

Add subtask

