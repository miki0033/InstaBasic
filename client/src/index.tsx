import "./index.css";
import React from "react";
import ReactDOM from "react-dom/client";

import { DataProvider } from "./components/main/DataProvider.tsx";
import { BrowserRouter } from "react-router-dom";
import { NextUIProvider } from "@nextui-org/react";
import { ThemeProvider } from "next-themes";

import App from "./components/main/App.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
	<React.StrictMode>
		<DataProvider>
			<BrowserRouter>
				<NextUIProvider>
					<ThemeProvider attribute="class" defaultTheme="dark">
						<App />
					</ThemeProvider>
				</NextUIProvider>
			</BrowserRouter>
		</DataProvider>
	</React.StrictMode>
);
