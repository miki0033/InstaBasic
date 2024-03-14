import { Routes, Route, Navigate } from "react-router-dom";

import { useData } from "./DataProvider";

import Layout from "../layout/Layout";

import Login from "../pages/login/Login";
import Register from "../pages/login/Register";

import Feed from "../pages/feed/Feed";

import Profile from "../pages/profile/Profile";
import Settings from "../pages/settings/Settings";

import PageNotFound from "../pages/PageNotFound";

function App() {
	const {
		state: { user },
	} = useData();

	return (
		<>
			<Routes>
				<Route path="/" element={<Layout />}>
					<Route index element={<Navigate to={!user.id ? "/login" : "/home"} />} />

					<Route path="/home">
						<Route index element={!user.id ? <Navigate to={"/login"} /> : <Feed />} />
					</Route>

					<Route path="/profile">
						<Route index element={!user.id ? <Navigate to={"/login"} /> : <Profile />} />

						<Route path="settings">
							<Route index element={!user.id ? <Navigate to={"/login"} /> : <Settings />} />
						</Route>
					</Route>

					<Route path="/login">
						<Route index element={<Login />} />

						<Route path="register">
							<Route index element={<Register />} />
						</Route>
					</Route>
				</Route>

				<Route path="*" element={<PageNotFound />} />
			</Routes>
		</>
	);
}

export default App;
