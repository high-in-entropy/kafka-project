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

            fetch('http://54.196.185.56/order/place', {
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
                // Return the text content of the response
                return response.text();
            })
            .then(data => {
                // Update statusDiv with the string response
                statusDiv.textContent = 'Order placed successfully! Response: ' + data;
                console.log('Response:', data);
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