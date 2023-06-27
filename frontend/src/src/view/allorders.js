import React, {useEffect, useState} from 'react';
import {getBooksList} from "../service/bookService";
import {Button, Input, DatePicker, Row, Col} from "antd";
import moment from "moment";
import TopNavigation from "../component/TopNavigation";
import SideMenuBar from "../component/SideMenuBar";
import {Link} from "react-router-dom";

const {RangePicker} = DatePicker;

const AllOrders = () => {
    const [orders, setOrders] = useState([]);
    const [books, setBooks] = useState([]);
    const [orderData, setOrderData] = useState(null);
    const [searchQuery, setSearchQuery] = useState("");
    const [selectedDateRange, setSelectedDateRange] = useState([]);

    const userId = localStorage.getItem('userId');
    console.log("订单页的用户现在是" + userId);

    useEffect(() => {
        getBooksList((bookList) => {
            setBooks(bookList);
        });
    }, []);

    useEffect(() => {
        fetch('/api/ordersById', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                "userId": userId
            }),
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('Failed to place order');
            })
            .then(data => {
                console.log('Order confirmed:', data);
                setOrderData(data.data); // 将data赋值给orderData
            })
            .catch(error => {
                console.error('Error:', error);
                throw new Error(error.message);
            });
    }, [userId]); // 在依赖项中添加userId

    const getBookDetails = (bookId) => {
        return books.find(book => book.id === bookId);
    };

    // Helper function to format the order date
    const formatDateTime = (dateTime) => {
        const options = {
            year: "numeric",
            month: "long",
            day: "numeric",
            hour: "numeric",
            minute: "numeric",
            second: "numeric",
        };
        return new Date(dateTime).toLocaleDateString(undefined, options);
    };

    // Group orders by orderId
    const groupedOrders = {};
    if (orderData) {
        orderData.orderItems.forEach((order) => {
            if (!groupedOrders[order.orderId]) {
                groupedOrders[order.orderId] = {
                    orders: [],
                    orderDate: formatDateTime(order.order.orderDate), // 格式化订单日期时间
                };
            }
            groupedOrders[order.orderId].orders.push(order);
        });
    }

    const handleSearchInputChange = (event) => {
        const query = event.target.value;
        setSearchQuery(query);
        searchBooks(query);
    };

    const handleDateRangeChange = (dates) => {
        setSelectedDateRange(dates);
    };

    const searchBooks = (query) => {
        getBooksList((bookList) => {
            const filteredBooks = bookList.filter((book) =>
                book.title.toLowerCase().includes(query.toLowerCase())
            );
            setBooks(filteredBooks);
        });
    };

    const filterOrdersByDateRange = (orders) => {
        if (selectedDateRange.length !== 2) {
            return orders;
        }
        const startDate = moment(selectedDateRange[0]).startOf('day');
        const endDate = moment(selectedDateRange[1]).endOf('day');
        return orders.filter(({orderDate}) => {
            const orderDateTime = moment(orderDate, 'YYYY-MM-DD HH:mm:ss');
            return orderDateTime.isBetween(startDate, endDate);
        });
    };

    const filteredOrders = filterOrdersByDateRange(Object.values(groupedOrders));
    let oi ;
    return (
        <div>
            <TopNavigation/>
            <div className="home-container">
                <SideMenuBar/>
                <div className="book-list" style={{marginLeft: '30px'}}>
                    <Row gutter={16}>
                        <Col span={15}>
                    <h1>All Orders</h1>
                        </Col>
                        <Col span={9}>
                    <Link to={"/userConsumptionDetails"}>
                        <Button style={{marginTop:'20px'}}>查看我的消费</Button>
                    </Link>
                        </Col>
                    </Row>
                    <h3></h3>
                    <div>
                        <h3>书名检索</h3>
                        <Input
                            placeholder="搜索书籍"
                            value={searchQuery}
                            onChange={handleSearchInputChange}
                        />
                        <Button onClick={searchBooks}></Button>
                    </div>
                    <div>
                        <h3>时间检索</h3>
                        <RangePicker value={selectedDateRange} onChange={handleDateRangeChange}/>
                    </div>
                    <h3></h3>

                    <div>
                        {filteredOrders.map(({orderId, orderDate, orders}) => {
                            const hasMatchingBooks = orders.some(order => {
                                console.log("订单号 "+order.orderId)
                                // console.log("订单号2 "+orderId)
                                oi = order.orderId;
                                const bookDetails = getBookDetails(order.bookDetailsId);
                                return bookDetails && bookDetails.title.toLowerCase().includes(searchQuery.toLowerCase());
                            });

                            if (!hasMatchingBooks) {
                                return null; // 不显示没有匹配书籍的订单
                            }

                            return (
                                <div key={orderId} style={{marginBottom: '20px'}}>
                                    <h2>Order ID: {oi}</h2>
                                    <table>
                                        <thead>
                                        {orders.some(order => {
                                            const bookDetails = getBookDetails(order.bookDetailsId);
                                            return bookDetails && bookDetails.title.toLowerCase().includes(searchQuery.toLowerCase());
                                        }) && (
                                            <tr>
                                                <th>Id</th>
                                                <th>Title</th>
                                                <th>Image</th>
                                                <th>Author</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Total Price</th>
                                                <th>Order Date</th>
                                                <th>Status</th>
                                            </tr>
                                        )}
                                        </thead>
                                        <tbody>
                                        {orders.map((order) => {
                                            const bookDetails = getBookDetails(order.bookDetailsId);
                                            if (bookDetails && bookDetails.title.toLowerCase().includes(searchQuery.toLowerCase())) {
                                                return (
                                                    <tr key={order.id}>
                                                        <td>{bookDetails.id}</td>
                                                        <td>{bookDetails.title}</td>
                                                        <td>
                                                            <img src={bookDetails.image} alt={bookDetails.title}
                                                                 style={{width: '100px', height: '100px'}}/>
                                                        </td>
                                                        <td>{bookDetails.author}</td>
                                                        <td>{bookDetails.price}</td>
                                                        <td>{order.quantity}</td>
                                                        <td>{(order.quantity * bookDetails.price).toFixed(2)}</td>
                                                        <td>{orderDate}</td>
                                                        <td>{order.status}</td>
                                                    </tr>
                                                );
                                            }
                                            return null;
                                        })}
                                        </tbody>
                                    </table>
                                </div>

                            );
                        })}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AllOrders;
