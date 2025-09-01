import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container">
      <div class="row">
        <div class="col">
          <h2>User Profile</h2>
          <p>Profile component - to be implemented</p>
        </div>
      </div>
    </div>
  `
})
export class ProfileComponent {}
