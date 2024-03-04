import { Avatar, Button } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";
import { motion, useDragControls } from "framer-motion";

const Carousel = () => {
	const controls = useDragControls();

	function startDrag(event: any) {
		controls.start(event);
	}

	//----------------------
	// Placeholder for friends with active stories
	const nFriends = 11;
	const friends = [];
	(function fillFriends() {
		for (let i = 0; i < nFriends - 1; i++) {
			friends.push(<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />);
		}
		friends.push(<Avatar isBordered color="warning" src="" className="w-16 h-16 my-auto flex-none" />);
	})();
	//----------------------

	return (
		<div className="w-full h-full py-2">
			<div
				onPointerDown={startDrag}
				className="w-3/4 h-24 mx-auto bg-content3 border-2 border-primary-400 rounded-md overflow-auto scrollbar-hide">
				<motion.div
					className="h-full mx-auto  rounded-md flex flex-row gap-5 px-5"
					drag="x"
					dragControls={controls}
					dragConstraints={{ top: 0, bottom: 0, right: 0, left: -16 + (nFriends >= 8 ? -((nFriends - 8) * 84 + 80) : 0) }}>
					<Button isIconOnly className="w-16 h-16 my-auto rounded-full flex-none" color="success">
						<PlusCircleIcon />
					</Button>

					{friends.map((el) => {
						return el;
					})}
				</motion.div>
			</div>
		</div>
	);
};

export default Carousel;
