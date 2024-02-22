import { Routes, Route, Navigate } from "react-router-dom";

import Layout from "../layout/Layout";

import PageNotFound from "../pages/PageNotFound";
import { useData } from "./DataProvider";
import { Login } from "../pages/login/Login";

function App() {
	const {
		state: { profile },
	} = useData();

	return (
		<>
			<Routes>
				<Route path="/">{profile ? <Route index element={<Navigate to={"/home"} />} /> : <Login />}</Route>

				<Route path="/home" element={<Layout />}>
					<Route index element={<>Homepage</>} />

					<Route path="profile">
						<Route index element={<>Profile</>} />

						<Route path="settings">
							<Route index element={<>Profile Settings</>} />
						</Route>
					</Route>
				</Route>

				<Route path="login">
					<Route index element={<>Login</>} />

					<Route path="register">
						<Route index element={<>Register</>} />
					</Route>
				</Route>

				<Route path="*" element={<PageNotFound />} />
			</Routes>
		</>
	);
}

export default App;
