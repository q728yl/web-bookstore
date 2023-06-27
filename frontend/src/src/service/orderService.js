
export function getAllOrdersByUserId(userId,cart) {
    fetch('/api/ordersByUserId', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify({
            "userId": userId
        }),
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Failed to place order');
        })
        .then(data => {
            console.log('Order confirmed:', data);
            return 'Order confirmed!';
        })
        .catch(error => {
            console.error('Error:', error);
            throw new Error(error.message);
        });
}


export const fetchOrdersById = (userId) => {
    return fetch("/api/ordersById", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userId: userId,
        }),
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("Failed to place order");
        })
        .then((data) => {
            console.log("Order confirmed:", data);
            return data.data;
        })
        .catch((error) => {
            console.error("Error:", error);
            throw new Error(error.message);
        });
};