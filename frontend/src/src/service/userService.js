export const getUserList = (callback) => {
    console.log('getUserList called');
    fetch("/api/getUserList")
        .then(response => response.json())
        .then(data => {
            // 在这里处理返回的数据，将用户列表传递给回调函数
            const userList = data.data.map(user => ({
                id: user.id,
                username: user.username,
                email: user.email,
                address: user.address,
                comments:user.comments,
                userType: user.user_type,
                status:user.status
            }));
            console.log(userList)
            callback(userList);
        })
        .catch(error => console.error(error));
}
export const getUserListByUserType = (callback) => {
    // console.log('getUserList called');
    fetch("/api/getUserListByUserType?userType=1")
        .then(response => response.json())
        .then(data => {
            // 在这里处理返回的数据，将用户列表传递给回调函数
            const userList = data.data.map(user => ({
                id: user.id,
                username: user.username,
                email: user.email,
                address: user.address,
                comments:user.comments,
                userType: user.user_type,
                status:user.status
            }));

            callback(userList);
        })
        .catch(error => console.error(error));
}
export const getUserListByUserType0 = (callback) => {
    // console.log('getUserList called');
    fetch("/api/getUserListByUserType?userType=0")
        .then(response => response.json())
        .then(data => {
            // 在这里处理返回的数据，将用户列表传递给回调函数
            const userList = data.data.map(user => ({
                id: user.id,
                username: user.username,
                email: user.email,
                address: user.address,
                comments:user.comments,
                userType: user.user_type,
                status:user.status
            }));

            callback(userList);
        })
        .catch(error => console.error(error));
}
