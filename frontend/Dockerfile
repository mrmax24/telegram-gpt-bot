FROM node:18
WORKDIR /usr/src/app
COPY package.json .
RUN npm install
COPY . .
CMD npm run start
EXPOSE 4200
