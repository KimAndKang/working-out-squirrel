import SocialLoginButton from './components/SocialLoginButton';

function App() {
  return (
    <div className='h-[100vh] bg-sq-dark text-white flex justify-center'>
      <div className='flex flex-col justify-center items-center gap-6'>
        <div>
          <img src='/big-logo.svg' />
        </div>
        <div>
          <h1>운동하는 다람쥐</h1>
        </div>
        <div className='flex flex-col gap-4'>
          <SocialLoginButton
            bgColor='bg-[#F2F2F2]'
            logoUrl='/google-logo.svg'
            text='구글로 로그인하기'
          />
          <SocialLoginButton
            bgColor='bg-[#FFE812]'
            logoUrl='/kakao-logo.svg'
            text='카카오로 로그인하기'
          />
          <SocialLoginButton
            bgColor='bg-[#5AC351]'
            logoUrl='/naver-logo.jpg'
            text='네이버로 로그인하기'
          />
        </div>
      </div>
    </div>
  );
}

export default App;
