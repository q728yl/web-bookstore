import React, { useState, useEffect } from 'react';
import { Button, Col, Input, Row } from "antd";
import '../css/shopcard.css'
import {deleteCartItem, purchaseCart} from '../service/cartSevice';
import { Link } from "react-router-dom";

import TopNavigation from "../component/TopNavigation";
import SideMenuBar from "../component/SideMenuBar";
import { getBooksList } from "../service/bookService";

export default function ShopCard() {
    const [cart, setCart] = useState([]);
    const [books, setBooks] = useState([]);
    const userId = localStorage.getItem('userId');

    useEffect(() => {
        getBooksList((bookList) => {
            setBooks(bookList);
        });
    }, []);

    useEffect(() => {
        if (userId) {
            fetch('/api/getCartById', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    "userId": userId
                }),
            })
                .then(response => response.json())
                .then(data => {
                    if (data.ok) {
                        setCart(data.data);
                    } else {
                        console.error(data.msg);
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }, [userId]);

    function handleClearAllCart() {
        localStorage.removeItem('cart');
        setCart([]);
    }

    function handlePurchase() {
        purchaseCart(userId, cart)
            .then((message) => {
                console.log(message.msg); // Print the message.msg property
                alert(message.msg);
                localStorage.removeItem('cart');
                window.location.reload();
                setCart([]);
            })
            .catch((error) => {
                console.log(error.message); // Print the error message
                alert(error.message);
            });
    }


    function handleDelete(bookId) {
        if (userId) {
            deleteCartItem(userId, bookId)
                .then(() => {
                    // 刷新页面
                    window.location.reload();
                })
                .catch(error => {
                    alert(error.message);
                });
        }
    }

    return (
        <div>
            <TopNavigation />
            <div className="home-container">
                <SideMenuBar />
                <div className="shopcard-container" style={{ marginLeft: '30px' }}>
                    <div>
                        <Row gutter={16}>
                            <Col span={18}>
                                <h1><strong>My Shopping Cart</strong></h1>
                            </Col>
                            <Col span={6}>
                                <Link to={`http://localhost:3000/shopcard/allorders`}>
                                    <Button type="primary" style={{ marginTop: '1em' }}>All Orders</Button>
                                </Link>
                            </Col>
                        </Row>
                    </div>
                    {/*<div className="search-container" style={{ marginTop: '20px' }}>*/}
                    {/*    <Input.Search placeholder="Search books..." enterButton />*/}
                    {/*</div>*/}
                    <button style={{ marginLeft: '20px' }} onClick={handlePurchase}>Purchase Now!</button>
                    <table>
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Image</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Operation</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cart.map((book) => {
                            const selectedBook = books.find((item) => item.id === book.bookDetailsId);
                            if (selectedBook) {
                                return (
                                    <tr key={book.id}>
                                        <td>{selectedBook.id}</td>
                                        <td>{selectedBook.title}</td>
                                        <td>
                                            <img src={selectedBook.image} alt={selectedBook.title} style={{ width: '100px', height: '100px' }} />
                                        </td>
                                        <td>{selectedBook.author}</td>
                                        <td>{selectedBook.price}</td>
                                        <td>{book.quantity}</td>
                                        <td>
                                            <button onClick={() => handleDelete(selectedBook.id)}>Delete</button>
                                        </td>
                                    </tr>
                                );
                            } else {
                                return null;
                            }
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
