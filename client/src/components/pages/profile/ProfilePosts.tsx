import { useData } from "../../main/DataProvider";
import AddPostCard from "./cards/AddPostCard";
import PostCard from "./cards/PostCard";

export const ProfilePosts = ({ reloadImgs, profilePosts }: { reloadImgs: () => void; profilePosts: IPost[] }) => {
	const {
		state: {
			profile: { profilename, id },
		},
	} = useData();

	return (
		<div key={profilename + id} className="w-full h-full px-14 pt-10 pb-5 flex flex-row flex-wrap justify-between gap-10">
			<AddPostCard signal={reloadImgs} key={0} />
			{profilePosts
				.slice(0)
				.reverse()
				.map((el, index) => {
					return (
						<>
							<PostCard post={el} key={el?.profile + index} />
						</>
					);
				})}
		</div>
	);
};
