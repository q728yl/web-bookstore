import {Menu, Layout, Row, Col, Button} from 'antd';
import React, {useEffect, useState} from 'react';
import bslogo from "../images/bslogo.png";
import touxiang from "../images/touxiang.jpg";
import "../css/TopNavigation.css";
import {Link, useLocation} from "react-router-dom";
import {getUserList} from "../service/userService";
const { Header } = Layout

const TopNavigation = () => {
    const [users, setUsers] = useState([]);
    const userId = localStorage.getItem('userId');

    console.log(userId)
    // console.log(users)
    //console.log(users[userId-1].username)
    return (
        <Header>
            <Row gutter={16}>
                <Col span={2}>
                <div className="logo" >
                    <img alt="logo" src={bslogo} style={{ width: '40px', height: '40px' }}   />
                </div>
                </Col>
                <Col span={11}>
                    <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['home']}>
                        <Menu.Item key="home">Home</Menu.Item>
                        {/*<Menu.Item key="about">About</Menu.Item>*/}
                        {/*<Menu.Item key="contact">Contact</Menu.Item>*/}
                    </Menu>
                </Col>

                <Col span={2}>
                    <div className="greeting" >
                      Welcome!
                    </div>

                </Col>
                <Col span={4}>
                    <div className="greeting" >
                        用户Id:{userId}
                    </div>

                </Col>
                <Col span={1}>
                    <div className="touxiang" >
                        <img alt="touxiang" src={touxiang} style={{ width: '40px', height: '40px' }}   />
                    </div>
                </Col>
                <Col span={1}></Col>
                <Col span={3}>
                    <div className="qiehuan" >
                        <Link to='/'>
                            <Button type="primary">切换账号</Button>
                        </Link>
                    </div>
                </Col>
            </Row>
        </Header>
    );
};

export default TopNavigation;
