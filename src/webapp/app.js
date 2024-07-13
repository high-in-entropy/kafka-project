document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('order-form');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the default form submission

            const orderId = document.getElementById('orderId').value;
            const product = document.getElementById('product').value;
            const quantity = document.getElementById('quantity').value;
            const order = {
                orderId: orderId,
                product: product,
                quantity: quantity
            };

            const statusDiv = document.getElementById('status');
            statusDiv.textContent = 'Submitting your order....';

            fetch('http://54.91.182.158/order/place', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(order)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                statusDiv.textContent = 'Order placed successfully!';
                console.log(data);
            })
            .catch(error => {
                statusDiv.textContent = 'There was a problem with your order: ' + error.message;
                console.error('Error:', error);
            });
        });
    } else {
        console.error('Element with ID "order-form" not found!');
    }
});
