type Props = {
  color: string;
  logoUrl: string;
  text: string;
};

export default function SocialLoginButton({ color, logoUrl, text }: Props) {
  return (
    <button className={`flex bg-[${color}] rounded-md py-4 px-6 gap-5`}>
      <img src={logoUrl} />
      <p className='text-black font-bold'>{text}</p>
    </button>
  );
}
