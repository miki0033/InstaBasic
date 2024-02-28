import { Dispatch, ReactNode, createContext, useContext, useReducer } from "react";

// context type
interface IState {
	user: IUser | undefined;
	profile: IProfile | undefined;
	follower: IFollow[];
	followed: IFollow[];
	posts: IPost[];
}

interface IDataContext {
	state: IState;
	dispatch: Dispatch<ACTIONTYPE>;
}

// reducer actions custom type
type ACTIONTYPE = { type: "LOG_IN" } | { type: "LOG_OUT" };

// context reducer - gestisco le varie actions possibili
const reducer = (state: IDataContext["state"], action: ACTIONTYPE) => {
	switch (action.type) {
		case "LOG_IN":
			return {
				...state,
			};
		case "LOG_OUT":
			return {
				...state,
			};
		default:
			return state;
	}
};

// create context
const DataContext = createContext<IDataContext | null>(null);

// context hook
const useData = () => {
	const dataContext = useContext(DataContext);
	if (!dataContext) {
		throw new Error("useStore cannot be used outside of <StoreContext.Provider>");
	}
	return dataContext;
};

// context provider wrapper component - inizializzo il valore del context fornendo accesso al reducer
const DataProvider = ({ children }: { children: ReactNode }) => {
	//Login Data example
	const login: IState = {
		user: {
			id: 1,
			userName: "Seris_Dantalian",
			email: "seris.dantalian@gmail.com",
		},
		profile: {
			id: 1,
			userName: "Seris_Dantalian",
			firstName: "Seris",
			lastName: "Dantalian",
			birthday: "",
			bio: "",
			avatarUrl: "http://localhost:4000/pfp/get/1.jpg",
			userId: 1,
		},
		follower: [],
		followed: [],
		posts: [
			{
				title: "Purple",
				description: "description",
				url: ["http://localhost:4000/post/get/1_1.jpg", "http://localhost:4000/post/get/1_2.png"],
				likes: 15,
				type: "carousel",
				profileId: 1,
				createdAt: "2024-02-25",
			},
			{
				title: "Ace",
				description: "description",
				url: ["http://localhost:4000/post/get/1_3.png"],
				likes: 20,
				type: "single",
				profileId: 1,
				createdAt: "2024-02-29",
			},
		],
	}; //*/

	/*/state init
	const un_logged = {
		user: undefined,
		profile: undefined,
		follower: [],
		followed: [],
		posts: [],
	}; //*/

	// gestiamo lo state del context con l'hook useReducer
	const [state, dispatch] = useReducer(reducer, login);

	return <DataContext.Provider value={{ state, dispatch }}>{children}</DataContext.Provider>;
};

export { useData, DataProvider };
