import React, { useState, useEffect } from 'react';
import {getBooksList} from "../service/bookService";
import TopNavigation from "../component/TopNavigation";
import SideMenuBar from "../component/SideMenuBar";
import {Button, Input} from "antd";

function BookList() {
    const [books, setBooks] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");
    useEffect(() => {
        getBooksList((bookList) => {
            setBooks(bookList);
        });
    }, []);
    const handleSearchInputChange = (event) => {
        const query = event.target.value;
        setSearchQuery(query);
        searchBooks(query);
    };

    const searchBooks = (query) => {
        getBooksList((bookList) => {
            const filteredBooks = bookList.filter((book) =>
                book.title.toLowerCase().includes(query.toLowerCase())
            );
            setBooks(filteredBooks);
        });
    };
    return (
        <div>
            <TopNavigation/>
            <div className="home-container" >
                <SideMenuBar/>
        <div className="book-list" style={{marginLeft:'30px'}}>
            <h1>Book List</h1>
            <div>
                <Input
                    placeholder="搜索书籍"
                    value={searchQuery}
                    onChange={handleSearchInputChange}
                />
                <Button onClick={searchBooks}></Button>
            </div>
            <div>
                <table>
                    <thead>
                    <tr>
                        <th>Stock</th>
                        <th>Title</th>
                        <th>IBSN</th>
                        <th>Image</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Rating</th>
                        <th>Description</th>
                        <th>Quantity</th>

                        {/*<th></th>*/}

                    </tr>
                    </thead>
                    <tbody>
                    {books.map((book) => (
                        <tr key={book.id}>
                            <td>{book.in_stock===true?"在售":"已下架"}</td>
                            <td>{book.title}</td>
                            <td>{book.ibsn}</td>
                            <td>
                                <img src={book.image} alt={book.title} style={{ width: '100px', height: '100px' }} />
                            </td>
                            <td>{book.author}</td>
                            <td>{book.price}</td>
                            <td>{book.rating}</td>
                            <td>{book.description}</td>
                            <td>{book.quantity}</td>

                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
            </div>
        </div>
    );
}

export default BookList;
