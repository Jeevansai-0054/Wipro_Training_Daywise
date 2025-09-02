import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'orders',
  template: `
  <div class="orders-container">
    <h2 class="title">My Orders</h2>

    <div *ngIf="orders.length === 0" class="empty-msg">
      <p>No orders found</p>
    </div>

    <div class="table-wrapper" *ngIf="orders.length > 0">
      <table class="orders-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Total</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let o of orders">
            <td>{{ o.id }}</td>
            <td>
              <span [ngClass]="{
                'badge-success': o.status === 'PLACED',
                'badge-warning': o.status === 'PENDING',
                'badge-danger': o.status === 'CANCELLED'
              }">{{ o.status }}</span>
            </td>
            <td>₹ {{ o.total | number: '1.2-2' }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  `,
  styles: [`
    .orders-container {
      padding: 20px;
      max-width: 900px;
      margin: auto;
    }

    .title {
      font-size: 1.8rem;
      margin-bottom: 20px;
      font-weight: bold;
      text-align: center;
      color: #1e3a8a;
    }

    .empty-msg {
      text-align: center;
      font-size: 1.2rem;
      color: gray;
      margin-top: 40px;
    }

    .table-wrapper {
      overflow-x: auto;
    }

    .orders-table {
      width: 100%;
      border-collapse: collapse;
      background: #fff;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      border-radius: 8px;
    }

    .orders-table th, .orders-table td {
      padding: 12px 16px;
      text-align: left;
      border-bottom: 1px solid #e5e7eb;
    }

    .orders-table th {
      background: #1e3a8a;
      color: white;
      font-weight: 600;
      text-transform: uppercase;
      font-size: 0.9rem;
    }

    .orders-table tr:hover {
      background: #f3f4f6;
      transition: 0.3s;
    }

    /* Status badges */
    .badge-success {
      background: #16a34a;
      color: white;
      padding: 4px 10px;
      border-radius: 12px;
      font-size: 0.8rem;
    }
    .badge-warning {
      background: #facc15;
      color: black;
      padding: 4px 10px;
      border-radius: 12px;
      font-size: 0.8rem;
    }
    .badge-danger {
      background: #dc2626;
      color: white;
      padding: 4px 10px;
      border-radius: 12px;
      font-size: 0.8rem;
    }

    /* Responsive */
    @media (max-width: 600px) {
      .orders-table th, .orders-table td {
        padding: 10px;
        font-size: 0.85rem;
      }
      .title {
        font-size: 1.4rem;
      }
    }
  `]
})
export class OrdersComponent implements OnInit {
  orders: any[] = [];

  constructor(private api: ApiService, private auth: AuthService) {}

  ngOnInit() {
    this.orders = [
      { id: 101, status: 'PLACED', total: 95000.00 },
      { id: 102, status: 'PENDING', total: 95000.00 }
    ];
  }
}
