# Stage 1: Build Angular app
FROM node:latest AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build -- --configuration=production

# Stage 2: Serve Angular app with nginx
FROM nginx:latest
COPY --from=build /app/dist/user-management-frontend/browser /usr/share/nginx/html
COPY src/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]
