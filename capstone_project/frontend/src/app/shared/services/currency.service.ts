import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor() { }

  /**
   * Convert USD price to INR (approximate conversion rate)
   * @param usdPrice Price in USD
   * @returns Price in INR
   */
  usdToInr(usdPrice: number): number {
    // Using approximate conversion rate (1 USD = 83 INR)
    return Math.round(usdPrice * 83);
  }

  /**
   * Format price in Indian Rupees
   * @param price Price in INR
   * @returns Formatted price string
   */
  formatInr(price: number): string {
    return `₹${price.toLocaleString('en-IN')}`;
  }

  /**
   * Format price in Indian Rupees from USD price
   * @param usdPrice Price in USD
   * @returns Formatted INR price string
   */
  formatPrice(usdPrice: number): string {
    const inrPrice = this.usdToInr(usdPrice);
    return this.formatInr(inrPrice);
  }
}
