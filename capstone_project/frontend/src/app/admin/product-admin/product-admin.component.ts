import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-admin',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container">
      <div class="row">
        <div class="col">
          <h2>Product Management</h2>
          <p>Product admin component - to be implemented</p>
        </div>
      </div>
    </div>
  `
})
export class ProductAdminComponent {}
