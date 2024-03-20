import { Dropdown, DropdownTrigger, DropdownMenu, DropdownItem, Avatar } from "@nextui-org/react";
import { useData } from "../components/main/DataProvider";
import { Link, useNavigate } from "react-router-dom";

const ProfileDropdown = () => {
	const {
		state,
		state: { user, profile },
		dispatch,
	} = useData();

	const navigate = useNavigate();
	const toRoot = () => navigate("/");

	const GETIMAGE = import.meta.env.VITE_PYGET;

	return (
		<Dropdown placement="bottom-start" className="border border-1 border-secondary-200">
			<DropdownTrigger>
				<Avatar
					isBordered
					color={user.id ? "primary" : "danger"}
					className="w-9 h-9 my-auto mr-20"
					src={profile?.avatarUrl ? GETIMAGE + profile.avatarUrl : ""}
				/>
			</DropdownTrigger>

			{user.id ? (
				<DropdownMenu aria-label="ProfileActions" variant="flat">
					<DropdownItem key="profile" className="h-14 gap-2" onPress={() => console.log(state)} textValue="Signed in as">
						<p className="font-semibold">Signed in as</p>
						<p className="font-semibold">{user?.email}</p>
					</DropdownItem>

					<DropdownItem key="toProfile" textValue="profile">
						<Link to={"/profile"}>
							<p>Profile</p>
						</Link>
					</DropdownItem>

					<DropdownItem key="toSettings" textValue="settings">
						<Link to={"/profile/settings"}>
							<p>Settings</p>
						</Link>
					</DropdownItem>

					<DropdownItem
						key="logout"
						color="danger"
						textValue="Log OUT"
						onPress={() => {
							dispatch({ type: "LOG_OUT" });
							toRoot();
						}}>
						<p>Log Out</p>
					</DropdownItem>
				</DropdownMenu>
			) : (
				<DropdownMenu aria-label="ProfileActions" variant="flat">
					<DropdownItem key="notLogged" className="h-18 gap-3" textValue="NOT Logged IN">
						<p className="font-semibold">You are NOT Logged IN</p>
					</DropdownItem>
				</DropdownMenu>
			)}
		</Dropdown>
	);
};

export default ProfileDropdown;
