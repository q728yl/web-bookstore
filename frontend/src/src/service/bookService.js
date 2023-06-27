export const getBooksList = (callback) => {
    fetch("/api/getList")
        .then(response => response.json())
        .then(data => {
            // 在这里处理返回的数据，将书籍列表传递给回调函数
            callback(data["Books"]);
        })
        .catch(error => console.error(error));
}
export function AddBook(title,author,ibsn,image,description,rating,quantity,in_stock,price) {
    return fetch('/api/addBook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify({
            "title": title,
            "author": author,
            "IBSN": ibsn,
            "image": image,
            "description": description,
            "rating": rating,
            "quantity": quantity,
            "in_stock": in_stock,
            "price": price
        }),
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Failed to add book');
        })
        .then(data => {
            console.log('add succeed:', data);
            return 'Add book succeed!';
        })
        .catch(error => {
            console.error('Error:', error);
            throw new Error(error.message);
        });
}
export function UpdateBook(bookId,title, author, ibsn, image, description, rating, quantity, in_stock, price) {

    return fetch('/api/updateBook', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify({
            "bookId":bookId,
            "title": title,
            "author": author,
            "IBSN": ibsn,
            "image": image,
            "description": description,
            "rating": rating,
            "quantity": quantity,
            "in_stock": in_stock,
            "price": price
        }),
    })

}