import {
	Modal,
	ModalContent,
	ModalBody,
	ModalFooter,
	Button,
	useDisclosure,
	ModalHeader,
	CardHeader,
	Input,
	Select,
	SelectItem,
} from "@nextui-org/react";
import { Card, CardBody, CardFooter } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";
import { ChangeEvent, useState } from "react";
import axios from "axios";
import FormData from "form-data";
import { useData } from "../../../main/DataProvider";

const AddPostCard = ({ signal }: { signal: () => void }) => {
	//DATA
	const {
		state: {
			token,
			type,
			profile: { id },
		},
	} = useData();

	//MODAL
	const { isOpen, onOpen, onOpenChange } = useDisclosure();

	//POST DATA
	const [newPost, updateNewPost] = useState<IPost>({ title: "", description: "", imageUrl: [], type: "single", profile: id });
	const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.currentTarget;
		updateNewPost({
			...newPost,
			[name]: value,
		});
	};

	//POST FILES DATA
	const [postFiles, changeFiles] = useState<File[]>();

	//CALL TO BACKEND FOR POST UPLOAD
	const upload = (urls: string[]) => {
		let toUpload = { ...newPost };
		toUpload.imageUrl = urls;

		const JNEW_POST = import.meta.env.VITE_NEW_POST;
		const config = {
			headers: { Authorization: type + " " + token },
		};

		axios.post(JNEW_POST, toUpload, config).then(() => {
			signal();
		});
	};

	//CALL TO RESOURCE SERVER FOR IMG UPLOAD
	const pyPost = async (file: any) => {
		const PYPOST_POST = import.meta.env.VITE_PYPOST_POST;

		let data = new FormData();
		data.append("file", file, file.name);

		//first Upload each img to resource server
		const url = await axios
			.post(PYPOST_POST, data, {
				headers: {
					accept: "application/json",
					"Content-Type": `multipart/form-data; boundary=${data.getBoundary}`,
				},
			})
			.then((response) => {
				return String(response.data.imageURL);
			});

		return url;
	};

	//MAIN UPLOAD FUNCTION
	const uploadPost = async () => {
		const urls = [];
		for await (const file of postFiles!) {
			const newUrl = await pyPost(file);
			urls.push(newUrl);
		}
		upload(urls);
	};

	return (
		<div>
			<Card shadow="sm" isPressable onPress={onOpen} className="w-60 h-60 border border-secondary-400">
				<CardHeader>
					<p className="mx-auto my-auto">Add New Post</p>
				</CardHeader>
				<CardBody>
					<PlusCircleIcon />
				</CardBody>
				<CardFooter className=""></CardFooter>
			</Card>

			<Modal isOpen={isOpen} onOpenChange={onOpenChange} size="3xl" backdrop="blur" className="my-10" scrollBehavior="inside">
				<ModalContent className="border-2 border-secondary-400">
					{(onClose) => (
						<>
							<ModalHeader>Add Post</ModalHeader>
							<ModalBody className="text-center">
								<Input
									name="title"
									type="text"
									label="Post Title"
									labelPlacement="outside"
									className="w-1/3 mx-auto p-2"
									isRequired
									color="secondary"
									value={newPost.title}
									onChange={handleInput}
									isClearable
									onClear={() => {
										updateNewPost({
											...newPost,
											title: "",
										});
									}}
									//
								/>

								<Input
									name="description"
									type="text"
									label="Post Description"
									labelPlacement="outside"
									className="w-2/3 mx-auto p-2"
									isRequired
									color="secondary"
									value={newPost.description}
									onChange={handleInput}
									isClearable
									onClear={() => {
										updateNewPost({
											...newPost,
											description: "",
										});
									}}
									//
								/>

								<Select
									name="type"
									label="Select Post type: "
									labelPlacement="outside"
									className="w-1/3 mx-auto p-2"
									isRequired
									color="secondary"
									defaultSelectedKeys={[newPost.type]}
									onChange={(e: ChangeEvent<HTMLSelectElement>) => {
										updateNewPost({
											...newPost,
											type: e.target.value as "single" | "carousel",
										});
										changeFiles([]);
									}}
									//
								>
									<SelectItem key={"single"} value={"single"}>
										Single
									</SelectItem>

									<SelectItem key={"carousel"} value={"carousel"}>
										Carousel
									</SelectItem>
								</Select>

								<div className="flex flex-col">
									<label
										htmlFor="fileInput"
										className="w-auto h-1/6 mx-auto bg-secondary py-1 px-2 text-md text-secondary-200 rounded-md">
										{"Chose File(s)"}
									</label>
									<input
										id="fileInput"
										hidden
										name="files"
										type="file"
										multiple={newPost.type == "carousel"}
										onChange={(e: ChangeEvent<HTMLInputElement>) => {
											e.target.files?.length && changeFiles(Array.from(e.target.files));
										}}
									/>
									{postFiles?.map((fname) => {
										return (
											<h1 key={fname.name} className="text-secondary">
												{fname.name}
											</h1>
										);
									})}
								</div>
							</ModalBody>

							<ModalFooter className="flex flex-row gap-5">
								<Button
									color="primary"
									onPress={async () => {
										await uploadPost();
										changeFiles([]);
										updateNewPost({ title: "", description: "", imageUrl: [], type: "single", profile: id });
										onClose();
									}}>
									POST
								</Button>

								<Button color="danger" variant="light" onPress={onClose}>
									Close
								</Button>
							</ModalFooter>
						</>
					)}
				</ModalContent>
			</Modal>
		</div>
	);
};

export default AddPostCard;
