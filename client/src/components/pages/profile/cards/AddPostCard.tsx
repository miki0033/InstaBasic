import { Modal, ModalContent, ModalBody, ModalFooter, Button, useDisclosure, ModalHeader, CardHeader } from "@nextui-org/react";
import { Card, CardBody, CardFooter } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";

const AddPostCard = () => {
	const { isOpen, onOpen, onOpenChange } = useDisclosure();

	return (
		<>
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
							<ModalHeader></ModalHeader>
							<ModalBody className="text-center">
								WIP <br /> [FORM FOR ADDING POST]
							</ModalBody>
							<ModalFooter>
								<Button color="danger" variant="light" onPress={onClose}>
									Close
								</Button>
							</ModalFooter>
						</>
					)}
				</ModalContent>
			</Modal>
		</>
	);
};

export default AddPostCard;
