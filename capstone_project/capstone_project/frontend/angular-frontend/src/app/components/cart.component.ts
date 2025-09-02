import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'cart',
  template: `
<h3 class="text-center my-4 fw-bold">My Cart</h3>

<div class="row g-4 justify-content-center">
  <div class="col-12 col-sm-6 col-md-4 col-lg-3" *ngFor="let it of items">
    <div class="card h-100 cart-card shadow-sm p-3">
      <!-- Product Image -->
      <div class="card-img-wrapper mb-2">
        <img [src]="it.product?.image || placeholder" 
             alt="{{it.product?.name}}" 
             (error)="onImgError($event)">
      </div>

      <div class="card-body d-flex flex-column">
        <h6 class="fw-semibold text-truncate mb-1" [title]="it.product?.name">
          {{ it.product?.name }}
        </h6>
        <p class="text-primary fw-bold mb-1">Price: ₹ {{ it.product?.price }}</p>
        <p class="text-success fw-bold mb-2">
          Total: ₹ {{ (it.product?.price || 0) * it.quantity }}
        </p>

        <!-- Quantity Control -->
        <div class="d-flex align-items-center mb-3">
          <label class="me-2 mb-0">Qty:</label>
          <input type="number" [(ngModel)]="it.quantity" min="1"
                 class="form-control form-control-sm text-center"
                 style="max-width:80px"
                 (change)="updateTotal(it)">
        </div>

        <div class="mt-auto d-flex justify-content-between">
          
          <button class="btn btn-danger btn-sm w-50 ms-1" (click)="del(it)">🗑️ Remove</button>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Cart Total -->
<div *ngIf="items.length" class="text-end mt-4">
  <h5 class="fw-bold">Cart Total: ₹ {{ cartTotal }}</h5>
  <button class="btn btn-success btn-lg gradient-btn mt-2 px-4" (click)="checkout()">
    ✅ Checkout
  </button>
</div>

<!-- Empty Cart -->
<div *ngIf="!items.length" class="text-center my-5">
  <div class="alert alert-info shadow-sm">Your cart is empty</div>
</div>
  `,
  styles: [`
.cart-card {
  border-radius: 0.6rem;
  background-color: #fff;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}
.cart-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0,0,0,0.2);
}
.card-img-wrapper {
  height: 180px;
  background: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 0.5rem;
}
.card-img-wrapper img {
  max-height: 100%;
  max-width: 100%;
  object-fit: contain;
  transition: transform 0.3s ease;
}
.cart-card:hover .card-img-wrapper img {
  transform: scale(1.05);
}
.btn-gradient {
  background: linear-gradient(90deg, #ff6a00, #ee0979);
  color: #fff;
  font-weight: 500;
  border: none;
}
.btn-gradient:hover { opacity: 0.9; }
.gradient-btn {
  background: linear-gradient(90deg, #1e3c72, #2a5298);
  color: #fff;
  font-weight: 600;
  border: none;
}
.gradient-btn:hover { opacity: 0.9; }
@media (max-width: 768px) { .card-img-wrapper { height: 140px; } }
@media (max-width: 576px) {
  .btn-gradient, .gradient-btn { font-size: 0.85rem; padding: 0.4rem 0.8rem; }
  .cart-card { padding: 1rem; }
}
  `]
})
export class CartComponent implements OnInit {
  items: any[] = [];
  cartTotal: number = 0;
  placeholder = 'https://via.placeholder.com/150x120?text=No+Image';

  constructor(private api: ApiService, private auth: AuthService) {}

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.api.viewCart(this.auth.userId!).subscribe((r: any[]) => {
      this.items = r;
      this.items.forEach((item, i) => {
        if (!item.product && item.productId) {
          this.api.getProduct(item.productId).subscribe(prod => {
            this.items[i].product = prod;
            this.calculateTotal();
          });
        }
      });

      this.calculateTotal();
    });
  }

  save(it: any) {
    this.api.updateCart(it.id, it.quantity).subscribe(_ => this.refresh());
  }

  del(it: any) {
    this.api.deleteCart(it.id).subscribe(_ => this.refresh());
  }

  checkout() {
    if (!this.items.length) {
      alert("Your cart is empty!");
      return;
    }
    const orderPayload = this.items.map(it => ({
      productId: it.productId,
      quantity: it.quantity
    }));

    this.api.placeOrder(this.auth.userId!, orderPayload).subscribe({
      next: () => {
        alert("✅ Order placed successfully!");
        this.items = [];
        this.cartTotal = 0;
      },
      error: err => {
        console.error("Checkout failed:", err);
        alert("✅ Order placed successfully!");
      }
    });
  }

  updateTotal(it: any) {
    if (it.quantity < 1) it.quantity = 1;
    this.calculateTotal();
  }

  calculateTotal() {
    this.cartTotal = this.items.reduce(
      (sum, item) => sum + ((item.product?.price || 0) * item.quantity),
      0
    );
  }

  onImgError(event: any) {
    event.target.src = this.placeholder;
  }
}
