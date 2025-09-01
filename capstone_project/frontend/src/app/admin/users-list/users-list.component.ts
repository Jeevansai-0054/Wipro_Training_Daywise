import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-users-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="container">
      <div class="row">
        <div class="col">
          <h2>User Management</h2>
          <p>Users list component - to be implemented</p>
        </div>
      </div>
    </div>
  `
})
export class UsersListComponent {}
