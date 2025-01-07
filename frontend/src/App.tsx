import { Routes, Route } from "react-router";
import LoginPage from "@/pages/user/Login";
import FeedPage from "@/pages/Feed";

export default function App() {
  return (
    <div className='h-[100vh] bg-sq-dark text-white flex justify-center'>
      <Routes>
        <Route path='login' element={<LoginPage />} />
        <Route path='/' element={<FeedPage />} />
      </Routes>
    </div>
  );
}
