"use client";

import { useEffect, useState } from "react";

import { Button } from "@nextui-org/react";

import { useTheme } from "next-themes";

import { SunIcon } from "./icons/SunIcon";
import { MoonIcon } from "./icons/MoonIcon";

export function ThemeSwitcher() {
	const [mounted, setMounted] = useState(false);
	const { theme, setTheme } = useTheme();

	useEffect(() => {
		setMounted(true);
	}, []);

	if (!mounted) return null;

	const LightModeButton = (
		<Button isIconOnly onClick={() => setTheme("light")} className="bg-yellow-500">
			<SunIcon />
		</Button>
	);

	const DarkModeButton = (
		<Button isIconOnly onClick={() => setTheme("dark")} className=" bg-gray-600">
			<MoonIcon />
		</Button>
	);

	return theme == "light" ? DarkModeButton : LightModeButton;
}
