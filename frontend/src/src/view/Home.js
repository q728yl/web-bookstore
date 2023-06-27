import "antd/dist/antd.css";
import '../css/Home.css';
import React, {useState, useEffect, useContext} from 'react';
import {Input, Button, Card, Row, Col, Carousel, List} from 'antd';
import { Link } from 'react-router-dom';
import { getBooksList } from '../service/bookService';
import SideMenuBar from "../component/SideMenuBar";
import TopNavigation from "../component/TopNavigation";
import { useLocation } from "react-router-dom";



const { Meta } = Card;
const default_url = "https://th.bing.com/th/id/R.785580b0aa9cce1c7e016db5ee2e078e?rik=ebpuQj03uKxGQg&riu=http%3a%2f%2fphotos.tuchong.com%2f255820%2ff%2f2852945.jpg&ehk=8sZ0LLnnaIXhdwT1M5Zk2xrfIMFcE%2bV45Nc1839Gj7Y%3d&risl=&pid=ImgRaw&r=0";
const Home = () => {
    const [books, setBooks] = useState([]);
    let userId = localStorage.getItem('userId');
    const location = useLocation();
    if(location.state != null){
        userId = location.state ? location.state.userId : null;
    }

    useEffect(() => {
        getBooksList((bookList) => {
            setBooks(bookList);
        });
    }, []);
    // console.log("home页现在的用户id是"+userId)
    localStorage.setItem('userId',userId)
    userId = localStorage.getItem('userId');
    console.log("home页现在的用户是"+userId)

    return (
        <div>
        <TopNavigation/>
        <div className="home-container">
        <SideMenuBar/>
        <div className="home" style={{marginLeft:'30px'}}>
            <div style={{marginTop: '40px', marginBottom:'40px'}}>
            {/*<div className="search-container">*/}
            {/*    <Input.Search placeholder="Search books..." enterButton />*/}
            {/*</div>*/}
            <div className="zoumadeng" style={{display: "flex", justifyContent: "center", alignItems: "center"}}>
                <Carousel className="caro" style={{ width: '800px', height: '300px'}}  autoplay>
                    <div>
                        <img alt="book" src={require("../images/image11.png")} style={{ width: '800px', height: '300px' }} />
                    </div>
                    <div >
                        <img alt="book" src={require("../images/image12.png")}  style={{ width: '800px', height: '300px' }}/>
                    </div>
                    <div>
                        <img alt="book" src={require("../images/image13.png")} style={{ width: '800px', height: '300px' }} />
                    </div>
                </Carousel>
            </div>
            </div>
            <List
                style={{margin: "20px"}}
                grid={{gutter: 16, column: 4}}
                dataSource={books.filter(book => book.in_stock === true).map(book => ({
                    ...book,
                    key: book.id // 添加一个唯一的键值，可以是book的id或其他唯一标识符
                }))}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 20,
                }}
                renderItem={(book) => (
                    <List.Item>
                        {/*navigate("/home", { state: { userId } });*/}
                        <Link to={`/book/${book.id}?userId=${userId}`}>
                            <Card
                                cover={<img alt={default_url}
                                            src={book.image? book.image: default_url}
                                            style={{width: "90%", margin: "10 auto"}}/>}
                                title={book.title}

                            >
                                简介
                                <Card.Meta description={book.description}/>
                                评分：{book.rating}
                                <br/>
                                价格：{book.price}
                                <br/>
                            </Card>
                        </Link>
                    </List.Item>
                )}
            />
            <h1>已下架书籍</h1>
            <List
                style={{margin: "20px"}}
                grid={{gutter: 16, column: 4}}
                dataSource={books.filter(book => book.in_stock === false).map(book => ({
                    ...book,
                    key: book.id // 添加一个唯一的键值，可以是book的id或其他唯一标识符
                }))}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 20,
                }}
                renderItem={(book) => (
                    <List.Item>
                        {/*navigate("/home", { state: { userId } });*/}
                        <Link to={`/book/${book.id}?userId=${userId}`}>
                            <Card
                                cover={<img alt={default_url}
                                            src={book.image? book.image: default_url}
                                            style={{width: "90%", margin: "10 auto"}}/>}
                                title={book.title}

                            >
                                简介
                                <Card.Meta description={book.description}/>
                                评分：{book.rating}
                                <br/>
                                价格：{book.price}
                                <br/>
                            </Card>
                        </Link>
                    </List.Item>
                )}
            />
            </div>
        </div>
        </div>
    );
};

export default Home;

