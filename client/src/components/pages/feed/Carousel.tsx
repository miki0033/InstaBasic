import { Avatar, Button } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";

const Carousel = () => {
	return (
		<div className="w-full h-full py-2">
			<div className="w-3/4 h-24 mx-auto bg-red-500 rounded-md flex flex-row gap-5 px-5 overflow-auto scrollbar-hide">
				<Button isIconOnly className="w-16 h-16 my-auto rounded-full flex-none" color="success">
					<PlusCircleIcon />
				</Button>
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
				<Avatar isBordered color="success" src="" className="w-16 h-16 my-auto flex-none" />
			</div>
		</div>
	);
};

export default Carousel;
