import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container">
      <div class="row">
        <div class="col">
          <h2>Admin Dashboard</h2>
          <p>Admin dashboard component - to be implemented</p>
        </div>
      </div>
    </div>
  `
})
export class DashboardComponent {}
