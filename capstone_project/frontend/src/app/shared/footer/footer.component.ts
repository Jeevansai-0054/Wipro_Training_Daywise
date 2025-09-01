import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule],
  template: `
    <footer class="bg-dark text-light py-4 mt-auto">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <h5>E-Commerce Store</h5>
            <p class="mb-0">Your one-stop shop for all your needs.</p>
          </div>
          <div class="col-md-6 text-md-end">
            <p class="mb-0">&copy; 2024 E-Commerce Store. All rights reserved.</p>
          </div>
        </div>
      </div>
    </footer>
  `,
  styles: [`
    footer {
      margin-top: auto;
    }
  `]
})
export class FooterComponent {}
