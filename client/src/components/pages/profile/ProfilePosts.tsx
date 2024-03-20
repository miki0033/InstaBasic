import axios from "axios";
import { useData } from "../../main/DataProvider";
import AddPostCard from "./cards/AddPostCard";
import PostCard from "./cards/PostCard";

export const ProfilePosts = () => {
	const {
		state: {
			profile: { profilename },
			token,
			type,
		},
	} = useData();

	const GET_POSTS = import.meta.env.VITE_GET_POSTS_BY_PROFILE_NAME;
	let profilePosts: IPost[] = [];
	const reloadImgs = () => {
		const config = {
			headers: { Authorization: type + " " + token },
		};
		axios.get(GET_POSTS + profilename, config).then((res) => {
			profilePosts = res.data.content;
			console.log(profilePosts);
		});
	};
	reloadImgs();

	return (
		<div className="w-full h-full px-14 pt-10 pb-5 flex flex-row flex-wrap justify-between gap-10">
			<AddPostCard signal={reloadImgs} />
			{profilePosts.map((el) => {
				return (
					<>
						<PostCard post={el} />
					</>
				);
			})}
		</div>
	);
};
