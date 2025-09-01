export interface Product {
  id: number;
  name: string;
  description?: string;
  price: number;
  quantity: number;
  sku: string;
  category?: string;
  imageUrl?: string;
  active: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface ProductCreateDto {
  name: string;
  description?: string;
  price: number;
  quantity: number;
  sku: string;
  category?: string;
  imageUrl?: string;
  active: boolean;
}

export interface ProductUpdateDto {
  id: number;
  name?: string;
  description?: string;
  price?: number;
  quantity?: number;
  sku?: string;
  category?: string;
  imageUrl?: string;
  active?: boolean;
}
