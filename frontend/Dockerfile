# Frontend Dockerfile
FROM node:20-alpine

# 작업 디렉토리 설정
WORKDIR /app

# package.json과 package-lock.json 복사
COPY package.json package-lock.json ./

# 의존성 설치
RUN npm install

# 소스 코드 복사
COPY . .

#v포트노출
EXPOSE 5173

CMD ["npm", "run", "dev"]