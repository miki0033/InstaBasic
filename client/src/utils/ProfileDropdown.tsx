import { Dropdown, DropdownTrigger, DropdownMenu, DropdownItem, Avatar } from "@nextui-org/react";
import { useData } from "../components/main/DataProvider";
import { Link, useNavigate } from "react-router-dom";

const ProfileDropdown = () => {
	const {
		state: { user, profile },
		dispatch,
	} = useData();

	const navigate = useNavigate();
	const toRoot = () => navigate("/");

	return (
		<Dropdown placement="bottom-start" className="border border-1 border-secondary-200">
			<DropdownTrigger>
				<Avatar
					isBordered
					color={user ? "primary" : "danger"}
					className="w-9 h-9 my-auto mr-20"
					src={profile?.avatarUrl ? profile.avatarUrl : ""}
				/>
			</DropdownTrigger>

			{user ? (
				<DropdownMenu aria-label="ProfileActions" variant="flat">
					<DropdownItem key="profile" className="h-14 gap-2">
						<p className="font-semibold">Signed in as</p>
						<p className="font-semibold">{user?.email}</p>
					</DropdownItem>

					<DropdownItem key="toProfile">
						<Link to={"/profile"}>
							<p>Profile</p>
						</Link>
					</DropdownItem>

					<DropdownItem key="toSettings">
						<Link to={"/profile/settings"}>
							<p>Settings</p>
						</Link>
					</DropdownItem>

					<DropdownItem
						key="logout"
						color="danger"
						onPress={() => {
							dispatch({ type: "LOG_OUT" });
							toRoot();
						}}>
						Log Out
					</DropdownItem>
				</DropdownMenu>
			) : (
				<DropdownMenu aria-label="ProfileActions" variant="flat">
					<DropdownItem key="notLogged" className="h-18 gap-3">
						<p className="font-semibold">You are NOT Logged IN</p>
					</DropdownItem>
				</DropdownMenu>
			)}
		</Dropdown>
	);
};

export default ProfileDropdown;
