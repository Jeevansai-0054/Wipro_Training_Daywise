-- Seed sample products
INSERT INTO products (name, description, price, quantity, sku, category, image_url, active, created_at, updated_at) VALUES
('Laptop Pro X1', 'High-performance laptop with latest Intel processor', 1299.99, 50, 'LAPTOP-X1-001', 'Electronics', 'https://example.com/laptop-x1.jpg', TRUE, NOW(), NOW()),
('Wireless Headphones', 'Premium noise-cancelling wireless headphones', 199.99, 100, 'HEADPHONES-001', 'Electronics', 'https://example.com/headphones.jpg', TRUE, NOW(), NOW()),
('Smart Watch Series 5', 'Feature-rich smartwatch with health monitoring', 299.99, 75, 'SMARTWATCH-005', 'Electronics', 'https://example.com/smartwatch.jpg', TRUE, NOW(), NOW()),
('Coffee Maker Deluxe', 'Programmable coffee maker with thermal carafe', 89.99, 30, 'COFFEE-001', 'Home & Kitchen', 'https://example.com/coffeemaker.jpg', TRUE, NOW(), NOW()),
('Yoga Mat Premium', 'Non-slip yoga mat with carrying strap', 39.99, 200, 'YOGA-001', 'Sports & Fitness', 'https://example.com/yogamat.jpg', TRUE, NOW(), NOW()),
('Bluetooth Speaker', 'Portable waterproof bluetooth speaker', 79.99, 60, 'SPEAKER-001', 'Electronics', 'https://example.com/speaker.jpg', TRUE, NOW(), NOW());
