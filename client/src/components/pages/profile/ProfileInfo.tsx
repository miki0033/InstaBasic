import { Avatar, Button } from "@nextui-org/react";
import { useData } from "../../main/DataProvider";

const ProfileInfo = ({ nPosts }: { nPosts: number }) => {
	const {
		state: { profile, followed, follower },
	} = useData();

	return (
		<div className="py-5">
			<div className="h-36 flex flex-row gap-10">
				<div className="my-auto ml-10">
					<Avatar src={profile?.avatarUrl} className="w-24 h-24" isBordered color="primary" />
				</div>

				<div className="py-5 my-auto flex flex-col">
					<p className="text-left text-2xl text-primary">@{profile?.profilename}</p>

					<div className="flex flex-row gap-3">
						<p className="text-left text-lg text-default-800 my-auto">{profile?.firstName + " " + profile?.lastName}</p>
						<p className="text-left text-xs text-default-800 my-auto"> {profile?.birthday} </p>
					</div>

					<p className="max-w-5/6 text-left text-sm text-default-500">{profile?.bio}</p>
				</div>
			</div>

			<div className="w-2/3 h-16 mx-auto flex flex-row justify-around gap-5">
				<Button disabled className="rounded-sm my-auto" color="secondary">
					Posts: {nPosts}
				</Button>
				<Button className="rounded-sm my-auto" color="secondary">
					Follower: {follower.length}
				</Button>
				<Button className="rounded-sm my-auto" color="secondary">
					Followed: {followed.length}
				</Button>
			</div>
		</div>
	);
};

export default ProfileInfo;
