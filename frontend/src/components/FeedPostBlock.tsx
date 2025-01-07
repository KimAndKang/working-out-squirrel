import { FeedPost } from "@/types/PostType";
import { getTimeElapsed } from "@/utils/time";
import { FaRegComment, FaRegHeart } from "react-icons/fa";

type Props = FeedPost;

export default function FeedPostBlock(props: Props) {
  const { postImageUrl, userEmail, profileUrl, text, createdAt } = props;
  const elapsedTime = getTimeElapsed(createdAt);

  return (
    <div>
      <div className='flex pb-3'>
        <div className='flex gap-3 items-center px-4'>
          <img
            src={profileUrl}
            alt='user profile'
            className='w-8 h-8 rounded-full'
          />
          <p className='text-sm'>{userEmail}</p>
          <p className='text-xs text-gray-300'>{elapsedTime}</p>
        </div>
      </div>
      <img src={postImageUrl} alt='post image' className='max-w-full' />
      <div className='flex p-3 gap-3'>
        <FaRegHeart />
        <FaRegComment />
      </div>
      <div>{text}</div>
    </div>
  );
}
